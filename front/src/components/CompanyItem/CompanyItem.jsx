import React from 'react';
import cls from "./CompanyItem.module.css";
import EditButton from "../../shared/UI/EditButton/EditButton";
import DeleteButton from "../../shared/UI/DeleteButton/DeleteButton";

const CompanyItem = ({company}) => {
    return (
        <div className={cls.company_folder}>
            <div className={cls.company_top_row}>
                <div className={cls.company_name}>{company.name}</div>
                <div className={cls.company_actions}>
                    <EditButton action={console.log("edit")}/>
                    <DeleteButton action={console.log("delete")}/>
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
        </div>
    );
};

export default CompanyItem;