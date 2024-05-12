import React, {useContext} from 'react';
import cls from "./CompanyItem.module.css";
import EditButton from "../../shared/UI/EditButton/EditButton";
import DeleteButton from "../../shared/UI/DeleteButton/DeleteButton";
import Modal from "../../shared/UI/Modal/Modal";
import Form from "../../enities/Form/Form";
import {Context} from "../../index";

const CompanyItem = ({company, wasChanged, setWasChanged}) => {
    const [isEditVisible, setIsEditVisible] = React.useState(false);
    const {store} = useContext(Context);
    const id = company.id + "";
    return (
        <div className={cls.company_folder}>
            <div className={cls.company_top_row}>
                <div className={cls.company_name}>{company.name} #{id.padStart(4, "0")}</div>
                <div className={cls.company_actions}>
                    <EditButton action={()=>{setIsEditVisible(true)}} />
                    <DeleteButton action={ () => {
                        store.companies.deleteCompany(company.id).then(() => {
                            setWasChanged(!wasChanged)
                        });
                    }} />
                </div>
            </div>

            <div className={cls.company_inn}>{company.inn}</div>
            <div className={cls.address_row}>
                <div className={cls.address}>
                    {company.physical_address}
                </div>
                <div className={cls.address}>
                    {company.legal_address}
                </div>
            </div>
            <div className={cls.sub_data}>
                <div>
                    {company.status}
                </div>
                <div>
                    {company.manager.username}
                </div>
            </div>

            <Modal
                visible={isEditVisible}
                setVisible={setIsEditVisible}
                >
                <Form
                    label={"Изменение компании"}
                    fields={[
                        {
                            type: 'text',
                            placeholder: 'ACTIVE',
                            label: 'Статус',
                            preset: company.status
                        },
                        {
                            type: 'text',
                            placeholder: 'ООО Рога и Копыта',
                            label: 'Название',
                            preset: company.name
                        },
                        {
                            type: 'text',
                            placeholder: '12345',
                            label: 'ИНН',
                            preset: company.inn
                        },
                        {
                            type: 'text',
                            placeholder: 'г. Кемерово, д.3',
                            label: 'Физический адрес',
                            preset: company.physical_address
                        },
                        {
                            type: 'text',
                            placeholder: 'г. Кемерово, д.3',
                            label: 'Юридический адрес',
                            preset: company.legal_address
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID менеджера',
                            preset: company.manager.id
                        },
                    ]}
                    basicAction={async (status, name, inn, p_address, l_address, manager_id) => {
                        store.companies.updateCompany(company.id, status, name, inn, p_address, l_address, manager_id).then(() => {
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

export default CompanyItem;