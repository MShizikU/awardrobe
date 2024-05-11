import React, {useContext, useEffect} from 'react';
import {useNavigate} from 'react-router-dom';
import Form from '../../enities/Form/Form'
import {Context} from "../../index";
import PageThemplate from "../../components/PageThemplate/PageThemplate";
import {observer} from "mobx-react-lite";

const Login = () => {
    const {store} = useContext(Context);
    const navigate = useNavigate()
    useEffect(() => {
        if (store.isAuth) {
            navigate('/user');
        }
    }, [navigate, store.isAuth]);
    return (
        <PageThemplate
            label = {'Вход'}
        >
            <Form
                label={"Введите данные для входа"}IsaUTH
                fields={[
                    {
                        type: 'text',
                        placeholder: 'Shiz',
                        label: 'Имя пользователя'
                    },
                    {
                        type: 'password',
                        placeholder: '12345',
                        label: 'Пароль'
                    },
                ]}
                basicAction={(username, password) => {
                    store.users.login(username, password).then(navigate("/"));
                }}
                secondaryAction={(username, password) => {
                    navigate('/registration')
                }}
                actionButtonText={"Вход"}
                secondaryButtonText={"Регистрация"}
                />
        </PageThemplate>
    );
};

export default Login;