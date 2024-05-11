import {makeAutoObservable} from "mobx";
import UserStore from "./modules/UserStore";
import {message, notification} from "antd";
import CompanyStore from "./modules/CompanyStore";

export default class AppStore {

    users = new UserStore(this);
    companies = new CompanyStore(this);

    userState = null;
    isAuthState = false;
    isSuperAdminState = null;

    get isAuth() {
        return this.isAuthState;
    }

    set isAuth(value) {
        this.isAuthState = value;
    }

    get user() {
        return this.userState;
    }

    set user(value) {
        this.userState = value;
    }

    get isSuperAdmin() {
        return this.isSuperAdminState;
    }

    set isSuperAdmin(value) {
        this.isSuperAdminState = value;
    }

    constructor() {
        makeAutoObservable(this, {
                users: false,
             companies: false,
            },
            {
                deep: true
            });
        this.checkAuth();
    }

    checkAuth = () => {
        const token = localStorage.getItem('token');
        if (token) {
            this.loadUser(token);
        }
    }

    loadUser = (token) => {
        const {id, email, role} = JSON.parse(atob(token.split('.')[1]));
        const username = JSON.parse(atob(token.split('.')[1])).sub;
        this.user = {id, username, email, role};
        this.isAuth = true;

        this.checkSuperAdmin();
    }

    checkSuperAdmin = async () => {
        if (this.user.username === 'superuser') {
            this.isSuperAdmin = true;
        } else {
            this.isSuperAdmin = false;
        }
    }

    logout = () => {
        console.log('logout')
        localStorage.removeItem('token');
        this.user = null;
        this.isAuth = false;
    }

    httpError = (e) => {
        // switch-case by http code
        const msg = 'Ошибка сервера';
        if (e.response) {
            switch (e.response?.status) {
                case 400 || 401:
                    if (e.response.data?.title === 'Constraint Violation') {
                        notification.error({
                            message: 'Ошибка валидации данных',
                            description: e.response.data?.violations?.map(v => <p>{v.message}</p>),
                        }, 10);
                    } else {
                        notification.error({
                            message: e.response.data?.title,
                            description: e.response.data?.detail,
                        }, 10);
                    }

                    break;

                case 403:
                    message.error('Недостаточно прав');
                    break;
                case 404:
                    message.error('Не найдено');
                    break;
                default:
                    notification.error({
                        message: e.response.data.title,
                        description: e.response.data.detail,
                    }, 10);
            }
        } else {
            message.error(msg);
        }
    }

    isAdmin(): boolean {
        // return true;
        return this.user?.role.name === 'ADMIN';
    }

    isExecutor(): boolean {
        return this.user?.role.name === 'EXECUTOR' || this.isAdmin();
    }
}