import {makeAutoObservable, values} from "mobx";
import $api from "../../../http";
import {message} from "antd";
import AppStore from "../AppStore";

export default class UserStore {
    rootStore: AppStore;

    isLoading = false;

    constructor(rootStore: AppStore) {
        makeAutoObservable(this);
        this.rootStore = rootStore;
    }

    get isLoadingState() {
        return this.isLoading;
    }

    set isLoadingState(state) {
        this.isLoading = state;
    }


    register = async (username, email, password) => {
        try {
            this.isLoadingState = true;
            delete values.confirm;
            const response = await $api.post('/auth/sign-up', {
                username: username,
                email: email,
                password: password,
                role: "USER"
            });
            localStorage.setItem('token', response.data.token);
            this.rootStore.loadUser(response.data.token);
            message.info('Добро пожаловать, ' + this.rootStore.user.username + "!");
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    login = async (username, password) => {
        try {
            this.isLoadingState = true;
            const response = await $api.post('/auth/sign-in', {username: username, password: password});
            localStorage.setItem('token', response.data.token);
            this.rootStore.loadUser(response.data.token);

            message.info('С возращением, ' + this.rootStore.user.username + "!");
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }


    getUsersByFilter = async (id, status, username, email, role_id ) => await this.rootStore.performRequest($api.post('/users/filter', {
        id: id,
        status: status,
        username: username,
        email: email,
        role_id: role_id
    }));

    changeRole = async (id, role) => {
        const requestData = {
            id: id,
            role: role
            // другие поля, если они есть
        };

        const json = JSON.stringify(requestData);
        try {
            await $api.put(`/users/change-role`, json);
            message.success('Роль успешно изменена');
        } catch (e) {
            this.rootStore.httpError(e);
        }
    }

    changePassword = async (values) => {
        try {
            const json = JSON.stringify({
                oldPassword: values.oldPassword,
                newPassword: values.newPassword
            });
            await $api.post(`/users/change-password`, json);
            message.success('Пароль успешно изменен');
            return true;
        } catch (e) {
            this.rootStore.httpError(e);
        }
        return false;
    }

    changeCompany = async (values) => {
        try {
            const json = JSON.stringify({
                id : values.id,
                company: values.company
            });
            await $api.post(`/users/change-company`, json);
            message.success('Имя пользователя успешно изменено');
            return true;
        } catch (e) {
            this.rootStore.httpError(e);
        }
        return false;
    }

    updateUser = async(id, status, email, role_id) => await this.rootStore.performRequest($api.put(`/users/${id}`, {
        status: status,
        email: email,
        role_id: role_id
    }));

    delete = async (values) => {
        try {
            const json = JSON.stringify(values);
            console.log(json)
            await $api.post(`/users/delete`, json);
            message.success('Аккаунт успешно удален');
            return true;
        } catch (e) {
            this.rootStore.httpError(e);
        }
        return false;
    }
}