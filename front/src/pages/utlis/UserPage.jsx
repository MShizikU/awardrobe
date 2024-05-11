import React, {useContext} from 'react';
import PageThemplate from "../../components/PageThemplate/PageThemplate";
import {Context} from "../../index";

const UserPage = () => {
    const {store} = useContext(Context);
    return (
        <PageThemplate
        label={"Пользователь"}>

        </PageThemplate>
    );
};

export default UserPage;