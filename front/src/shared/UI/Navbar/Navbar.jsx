import React, {useContext} from 'react';
import {Link, Route, Routes, useNavigate} from "react-router-dom";
import cls from "./Navbar.module.css";
import {Context} from "../../../index";
import {observer} from "mobx-react-lite";

const Navbar = () => {
    const {store} = useContext(Context);
    const navigate = useNavigate();

    return (
        <div className={cls.navbar}>
            <div className={cls.navbar_content}>
                <div className={cls.navbar_label}>
                    <Link to="/">AWARDROBE</Link>
                </div>
                <div className={cls.navbar_links_block}>
                    {
                        store.isAuth
                        ?
                            store.isAdmin()
                            ?
                            <div className={cls.navbar_links}>
                                <Link to="/admin/companies">Компании</Link>
                                <Link to="/admin/branches">Филиалы</Link>
                                <Link to="/admin/agrs">Ряды</Link>
                                <Link to="/admin/cells">Ячейки</Link>
                            </div>
                            :
                            store.isExecutor()
                                ?
                                <div className={cls.navbar_links}>
                                    <Link to="/company">Ряд</Link>
                                </div>
                                :
                                <div className={cls.navbar_links}>
                                </div>
                        :
                        <div className={cls.navbar_links}></div>

                    }

                </div>
                <div className={cls.navbar_user}>
                    {
                    store.isAuth
                        ? <div className={cls.navbar_user_icon}><Link to="/user"></Link></div>
                    : <Link to="/login">Вход</Link>
                    }
                </div>
            </div>
        </div>
    );
};

export default observer(Navbar);