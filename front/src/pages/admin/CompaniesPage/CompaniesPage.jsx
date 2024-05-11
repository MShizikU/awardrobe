import React, {useContext, useEffect, useState} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import {Context} from "../../../index";
import CompanyItem from "../../../components/CompanyItem/CompanyItem";
import cls from "./CompaniesPage.module.css"

const CompaniesPage = () => {
    const {store} = useContext(Context);
    const [companiesList, setCompaniesList] = useState([]);

    const fetchWishlistWithGifts = async () => {
        const companies = await store.companies.getAllCompanies();
        console.log(companies)
        setCompaniesList(companies);
    };

    useEffect(() => {
        fetchWishlistWithGifts().then();
    }, [store.companies]);
    return (
        <PageThemplate
        label="Компании">
            <div className={cls.companies_wrapper}>
                {companiesList.map(comp =>
                    <CompanyItem key={comp.id} company={comp} />
                )}
            </div>

        </PageThemplate>
    );
};

export default CompaniesPage;