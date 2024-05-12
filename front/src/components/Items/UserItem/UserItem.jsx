import React, {useContext} from 'react';
import cls from "./UserItem.module.css";
import {Context} from "../../../index";
import Form from "../../../enities/Form/Form";
import Modal from "../../../shared/UI/Modal/Modal";
import EditButton from "../../../shared/UI/EditButton/EditButton";
import DeleteButton from "../../../shared/UI/DeleteButton/DeleteButton";

const UserItem = ({user, wasChanged, setWasChanged}) => {
    const id = user.id + "";
    const [isEditVisible, setIsEditVisible] = React.useState(false);
    const {store} = useContext(Context);
    return (
        <div className={cls.user_item}>
            <div className={cls.user_top_row}>
                <div className={cls.user_name}>
                    {user.username}
                    <div className={cls.user_id}>
                        #{id.padStart(4, "0")}
                    </div>
                </div>
                <div className={cls.user_actions}>
                    <EditButton action={() => {
                        setIsEditVisible(true)
                    }}/>
                    <DeleteButton action={() => {
                        store.users.delete(user.id).then(() => {
                            setWasChanged(!wasChanged)
                        });
                    }}/>
                </div>
            </div>

            <div className={cls.user_email}>
                {user.email}
            </div>
            <div className={cls.user_sub_data}>
                <div>
                    {user.status}
                </div>
                <div>
                    {user.role.name}
                </div>
            </div>

            <Modal
                visible={isEditVisible}
                setVisible={setIsEditVisible}
            >
                <Form
                    label={"Изменение пользователя"}
                    fields={[
                        {
                            type: 'text',
                            placeholder: 'ACTIVE',
                            label: 'Статус',
                            preset: user.status
                        },
                        {
                            type: 'text',
                            placeholder: 'ACTIVE',
                            label: 'email',
                            preset: user.email
                        },
                        {
                            type: 'text',
                            placeholder: 'ACTIVE',
                            label: 'ID роли',
                            preset: user.role.id
                        }
                    ]}
                    basicAction={async (status, name, inn, p_address, l_address, manager_id) => {
                        store.users.updateUser(user.id, status, name, inn, p_address, l_address, manager_id).then(() => {
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

export default UserItem;