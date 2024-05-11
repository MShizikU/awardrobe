import React, {useContext, useEffect, useState} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import {Context} from "../../../index";
import CompanyItem from "../../../components/CompanyItem/CompanyItem";
import cls from "./CompaniesPage.module.css"
import {observer} from "mobx-react-lite";

const CompaniesPage = () => {
    const {store} = useContext(Context);
    const [companiesList, setCompaniesList] = useState([]);
    const [wasChanged, setWasChanged] = useState(false);

    const fetchCompanies = async () => {
        const companies = await store.companies.getAllCompanies();
        console.log(companies)
        setCompaniesList(companies);
    };

    useEffect(() => {
        console.log("triggered");
        fetchCompanies().then();
    }, [wasChanged]);
    return (
        <PageThemplate
        label="Компании">
            <div className={cls.companies_wrapper}>
                {companiesList.map(comp =>
                    <CompanyItem key={comp.id} company={comp} wasChanged={wasChanged} setWasChanged={setWasChanged} />
                )}
            </div>

        </PageThemplate>
    );
};

export default observer(CompaniesPage);