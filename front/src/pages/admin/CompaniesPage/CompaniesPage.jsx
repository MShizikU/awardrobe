import React, {useContext, useEffect, useState} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import {Context} from "../../../index";
import CompanyItem from "../../../components/CompanyItem/CompanyItem";
import cls from "./CompaniesPage.module.css"
import {observer} from "mobx-react-lite";
import Button from "../../../shared/UI/Button/Button";
import Form from "../../../enities/Form/Form";
import Modal from "../../../shared/UI/Modal/Modal";

const CompaniesPage = () => {
    const {store} = useContext(Context);
    const [companiesList, setCompaniesList] = useState([]);
    const [wasChanged, setWasChanged] = useState(false);
    const [isAddVisible, setIsAddVisible] = useState(false);

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
            <Button
                action={(e) => setIsAddVisible(true)}
                className={"main"}
            >
                Добавить
            </Button>
            <Modal
                visible={isAddVisible}
                setVisible={setIsAddVisible}
            >
                <Form
                    label={"Добавление компании"}
                    fields={[
                        {
                            type: 'text',
                            placeholder: 'ACTIVE',
                            label: 'Статус'
                        },
                        {
                            type: 'text',
                            placeholder: 'ООО Рога и Копыта',
                            label: 'Название'
                        },
                        {
                            type: 'text',
                            placeholder: '12345',
                            label: 'ИНН'
                        },
                        {
                            type: 'text',
                            placeholder: 'г. Кемерово, д.3',
                            label: 'Физический адрес'
                        },
                        {
                            type: 'text',
                            placeholder: 'г. Кемерово, д.3',
                            label: 'Юридический адрес'
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID менеджера'
                        },
                    ]}
                    basicAction={async (status, name, inn, p_address, l_address, manager_id) => {
                        store.companies.createCompany( status, name, inn, p_address, l_address, manager_id).then(() => {
                            setWasChanged(!wasChanged)
                        });
                        setIsAddVisible(false);
                    }}
                    secondaryAction={(username, password) => {
                        setIsAddVisible(false)
                    }}
                    actionButtonText={"Сохранить"}
                    secondaryButtonText={"Отменить"}
                ></Form>
            </Modal>

        </PageThemplate>
    );
};

export default observer(CompaniesPage);