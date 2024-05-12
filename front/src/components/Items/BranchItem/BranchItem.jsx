import React, {useContext} from 'react';
import EditButton from "../../../shared/UI/EditButton/EditButton";
import DeleteButton from "../../../shared/UI/DeleteButton/DeleteButton";
import Modal from "../../../shared/UI/Modal/Modal";
import Form from "../../../enities/Form/Form";
import cls from "./BranchItem.module.css";
import {Context} from "../../../index";

const BranchItem = ({branch, wasChanged, setWasChanged}) => {
    const [isEditVisible, setIsEditVisible] = React.useState(false);
    const {store} = useContext(Context);
    const id = branch.id + "";
    return (
        <div className={cls.branch_folder}>
            <div className={cls.branch_top_row}>
                <div className={cls.branch_name}>{branch.name} #{id.padStart(4, "0")}</div>
                <div className={cls.branch_actions}>
                    <EditButton action={() => {
                        setIsEditVisible(true)
                    }}/>
                    <DeleteButton action={() => {
                        store.branches.deleteBranch(branch.id).then(() => {
                            setWasChanged(!wasChanged)
                        });
                    }}/>
                </div>
            </div>
            <div className={cls.branch_company}>
                Company: {branch.company_id}
            </div>
            <div className={cls.sub_data}>
                <div>
                    {branch.status}
                </div>
                <div>
                    Manager: {branch.manager_id}
                </div>
            </div>

            <Modal
                visible={isEditVisible}
                setVisible={setIsEditVisible}
            >
                <Form
                    label={"Обновление филиала"}
                    fields={[
                        {
                            type: 'text',
                            placeholder: 'ACTIVE',
                            label: 'Статус',
                            preset: branch.status
                        },
                        {
                            type: 'text',
                            placeholder: 'ООО Рога и Копыта',
                            label: 'Название',
                            preset: branch.name
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID менеджера',
                            preset: branch.manager_id
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID компании',
                            preset: branch.company_id
                        },
                    ]}
                    basicAction={async (status, name, manager_id, company_id) => {
                        store.branches.updateBranch(branch.id, status, name,  manager_id, company_id).then(() => {
                            setWasChanged(!wasChanged)
                        });
                        setIsEditVisible(false);
                    }}
                    secondaryAction={(username, password) => {
                        setIsEditVisible(false)
                    }}
                    actionButtonText={"Сохранить"}
                    secondaryButtonText={"Отменить"}
                ></Form>
            </Modal>
        </div>
    );
};

export default BranchItem;