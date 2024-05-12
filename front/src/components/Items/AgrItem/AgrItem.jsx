import React, {useContext} from 'react';
import cls from "./AgrItem.module.css";
import EditButton from "../../../shared/UI/EditButton/EditButton";
import DeleteButton from "../../../shared/UI/DeleteButton/DeleteButton";
import Modal from "../../../shared/UI/Modal/Modal";
import Form from "../../../enities/Form/Form";
import {Context} from "../../../index";

const AgrItem = ({agr, wasChanged, setWasChanged}) => {
    const [isEditVisible, setIsEditVisible] = React.useState(false);
    const {store} = useContext(Context);
    const id = agr.id + "";

    return (
        <div className={cls.agr_folder}>
            <div className={cls.agr_top_row}>
                <div className={cls.agr_name}>Ряд #{id.padStart(4, "0")}</div>
                <div className={cls.agr_actions}>
                    <EditButton action={() => {
                        setIsEditVisible(true)
                    }}/>
                    <DeleteButton action={() => {
                        store.agrs.deleteAgr(agr.id).then(() => {
                            setWasChanged(!wasChanged)
                        });
                    }}/>
                </div>
            </div>
            <div className={cls.agr_company}>
                Филиал: {agr.branch_id}
            </div>
            <div className={cls.sub_data}>
                <div>
                    {agr.status}
                </div>
                <div>
                    Исполнитель: {agr.executor_id}
                </div>
            </div>

            <Modal
                visible={isEditVisible}
                setVisible={setIsEditVisible}
            >
                <Form
                    label={"Добавление ряда"}
                    fields={[
                        {
                            type: 'text',
                            placeholder: 'active',
                            label: 'Статус',
                            preset: agr.status
                        },
                        {
                            type: 'text',
                            placeholder: '09:00',
                            label: 'Время открытия',
                            preset: agr.open_time
                        },
                        {
                            type: 'text',
                            placeholder: '18:00',
                            label: 'Время закрытия',
                            preset: agr.close_time
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID исполнителя',
                            preset: agr.executor_id
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID филиала',
                            preset:  agr.branch_id
                        },
                    ]}
                    basicAction={async (status, openTime, closeTime, executor_id, branch_id) => {
                        store.agrs.updateAgr(agr.id, status, openTime, closeTime, executor_id, branch_id ).then(() => {
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

export default AgrItem;