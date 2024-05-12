import React, {useContext, useEffect, useState} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import cls from "./ChooseAgrPage.module.css";
import {Context} from "../../../index";
import {useNavigate, useParams} from "react-router-dom";
import UserChooseItem from "../../../components/Items/UserChooseItem/UserChooseItem";
import ListFilter from "../../../components/ListFilter/ListFilter";
import ItemList from "../../../enities/ItemList/ItemList";

const ChooseAgrPage = () => {
    const {store} = useContext(Context);
    const params = useParams();
    const navigate = useNavigate();
    const branchId = params.id;
    const [optimalAgr, setOptimalAgr] = useState(null);
    useEffect(() => {
        async function fetchOptimalAgr() {
            setOptimalAgr(await store.agrs.getOptimal(branchId))
        }

        fetchOptimalAgr();
    }, []);
    return (
        <PageThemplate label ="Выберите ряд">
            <div className={cls.recomended_agr} style={{cursor: optimalAgr != null ? "pointer" : "default"}} onClick={() => {if (optimalAgr !== undefined && optimalAgr !== null) navigate(`/agr/${optimalAgr.id}`)}}>
                {optimalAgr !== undefined && optimalAgr !== null
                    ? "Рекомендован ряд #" + (optimalAgr.id).toString().padStart(4, "0")
                    : "Все ряды заняты"
                }
            </div>
            <ItemList
                fetchItems={(filter) => store.agrs.getAgrByFilter(filter.id, "active", null ,null, null, branchId)}
                createItem={null}

                renderItem={({ key, item, wasChanged, setWasChanged }) => (
                    <UserChooseItem key={key} item={item} baseUrl={"agr"} text={"Ряд #" + item.id.toString().padStart(4, "0")}/>
                )}

                ListFilter={({ filter, setFilter }) => (
                    <ListFilter filter={filter} setFilter={setFilter} />
                )}

                filterFields={
                    [
                        { name: 'id', placeholder: 'ID', label: 'ID' }
                    ]
                }
            />

        </PageThemplate>
    );
};

export default ChooseAgrPage;