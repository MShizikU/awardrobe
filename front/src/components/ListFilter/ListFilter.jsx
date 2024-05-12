import React from 'react';
import Input from "../../shared/UI/Input/Input";
import FilterInput from "../../shared/UI/FilterInput/FilterInput";

const ListFilter = ({filter, setFilter}) => {
    return (
        <div>
            <FilterInput
                type = {""}
                placeholder={"ID"}
                value={filter.id}
                label={'Поиск по ID'}
                onChange={e => setFilter({...filter, id: e.target.value})}
            />
            <FilterInput
                type = {""}
                placeholder={"Status"}
                value={filter.id}
                label={'Поиск по статусу'}
                onChange={e => setFilter({...filter, status: e.target.value})}
            />
        </div>
    );
};

export default ListFilter;