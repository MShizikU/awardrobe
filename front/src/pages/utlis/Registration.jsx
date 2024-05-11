import React, {useContext} from 'react';
import {Context} from "../../index";
import {useNavigate} from "react-router-dom";
import PageThemplate from "../../components/PageThemplate/PageThemplate";
import Form from "../../enities/Form/Form";

const Registration = () => {

    const {store} = useContext(Context);
    const navigate = useNavigate()
    return (
        <PageThemplate
            label = {'Регистрация'}
        >
            <Form
                label={"Введите данные для регистрации"}
                fields={[
                    {
                        type: 'text',
                        placeholder: 'Shiz',
                        label: 'Имя пользователя',
                        autocomplete: "off",
                    },
                    {
                        type: 'text',
                        placeholder: 'shiz@mail.ru',
                        label: 'Email',
                        autocomplete: "off",
                    },
                    {
                        type: 'password',
                        placeholder: '12345',
                        label: 'Пароль',
                        autocomplete: "off",
                    },
                ]}
                basicAction={(username, email, password) => {
                    store.users.register(username, email, password).then(navigate("/"));
                }}
                secondaryAction={(username, password) => {
                    navigate('/login')
                }}
                actionButtonText={"Регистрация"}
                secondaryButtonText={"Вход"}
            />
        </PageThemplate>
    );
};

export default Registration;