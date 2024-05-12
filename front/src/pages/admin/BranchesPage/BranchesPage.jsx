import React, {useContext, useEffect, useState} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import {Context} from "../../../index";
import Button from "../../../shared/UI/Button/Button";
import Modal from "../../../shared/UI/Modal/Modal";
import Form from "../../../enities/Form/Form";
import cls from "./BranchesPage.module.css";
import BranchItem from "../../../components/BranchItem/BranchItem";

const BranchesPage = () => {

    const {store} = useContext(Context);
    const [branchesList, setBranchesList] = useState([]);
    const [wasChanged, setWasChanged] = useState(false);
    const [isAddVisible, setIsAddVisible] = useState(false);

    const fetchBranches = async () => {
        const branches = await store.branches.getAllBranches();
        setBranchesList(branches);
    };

    useEffect(() => {
        fetchBranches().then();
    }, [wasChanged]);

    return (
        <PageThemplate
            label="Филиалы">
            <div className={cls.branches_wrapper}>
                {branchesList.map(branch =>
                    <BranchItem key={branch.id} branch={branch} wasChanged={wasChanged} setWasChanged={setWasChanged} />
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
                    label={"Добавление филиала"}
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
                            placeholder: '1',
                            label: 'ID менеджера'
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID компании'
                        },
                    ]}
                    basicAction={async (status, name, manager_id, company_id) => {
                        store.branches.createBranch( status, name, manager_id, company_id ).then(() => {
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

export default BranchesPage;