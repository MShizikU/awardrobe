import React from 'react';
import Input from "../../shared/UI/Input/Input";
import FilterInput from "../../shared/UI/FilterInput/FilterInput";
import cls from "./ListFilter.module.css";

const ListFilter = ({filterFields, filter, setFilter}) => {
    return (
        <div className={cls.filter_wrapper}>
                {filterFields.map(field => (
                    <FilterInput
                        key={field.name}
                        type=""
                        placeholder={field.placeholder}
                        value={filter[field.name]}
                        label={`Поиск по ${field.label}`}
                        onChange={e => setFilter({...filter, [field.name]: e.target.value})}
                    />
                ))}
        </div>
    );
};

export default ListFilter;