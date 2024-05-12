import React, {useContext, useEffect, useState} from 'react';
import {Context} from "../../../index";
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import cls from "./AgrsPage.module.css";
import Button from "../../../shared/UI/Button/Button";
import Modal from "../../../shared/UI/Modal/Modal";
import Form from "../../../enities/Form/Form";
import AgrItem from "../../../components/AgrItem/AgrItem";

const AgrsPage = () => {
    const {store} = useContext(Context);
    const [agrsList, setAgrsList] = useState([]);
    const [wasChanged, setWasChanged] = useState(false);
    const [isAddVisible, setIsAddVisible] = useState(false);

    const fetchAgrs = async () => {
        const agrs = await store.agrs.getAllAgrs();
        setAgrsList(agrs);
    };

    useEffect(() => {
        fetchAgrs().then();
    }, [wasChanged]);

    return (
        <PageThemplate
            label="Ряды">
            <div className={cls.agrs_wrapper}>
                {agrsList.map(agr =>
                    <AgrItem key={agr.id} agr={agr} wasChanged={wasChanged} setWasChanged={setWasChanged} />
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
                    label={"Добавление ряда"}
                    fields={[
                        {
                            type: 'text',
                            placeholder: 'active',
                            label: 'Статус'
                        },
                        {
                            type: 'text',
                            placeholder: '09:00',
                            label: 'Время открытия'
                        },
                        {
                            type: 'text',
                            placeholder: '18:00',
                            label: 'Время закрытия'
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID исполнителя'
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID филиала'
                        },
                    ]}
                    basicAction={async (status, openTime, closeTime, executor_id, branch_id) => {
                        store.agrs.createAgr( status, openTime, closeTime, executor_id, branch_id ).then(() => {
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

export default AgrsPage;