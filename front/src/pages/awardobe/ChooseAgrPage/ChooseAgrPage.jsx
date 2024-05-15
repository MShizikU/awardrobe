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
    const [branchId, setBranchId] = useState(params.id);
    const [optimalAgr, setOptimalAgr] = useState(null);
    useEffect(() => {
        store.cells.getCurrentCell().then((response) => response != null ? navigate(`/agr/${response.agr_id}`) : "")

        async function fetchOptimalAgr(){
            await store.getCurrentUser().then(async (result) => {
                store.user = result;
                if (branchId == null) setBranchId(store.user.branch_id);
                if (store.user.role.name !== "EXECUTOR") setOptimalAgr(await store.agrs.getOptimal(branchId))
            })

        }


        fetchOptimalAgr();
    }, []);
    return (
        <PageThemplate label ="Выберите ряд">
            {
                store.isLoading || branchId == null ? <div></div> :
                <div>
                    {
                        store.user.role.name !== "EXECUTOR" ?
                        <div className={cls.recomended_agr} style={{cursor: optimalAgr != null ? "pointer" : "default"}}
                             onClick={() => {
                                 if (optimalAgr !== undefined && optimalAgr !== null) navigate(`/agr/${optimalAgr.id}`)
                             }}>
                            {optimalAgr !== undefined && optimalAgr !== null
                                ? "Рекомендован ряд #" + (optimalAgr.id).toString().padStart(4, "0")
                                : "Все ряды заняты"
                            }
                        </div>
                            :<div></div>
                    }
                    <ItemList
                        fetchItems={(filter) => store.agrs.getAgrByFilter(filter.id, store.isExecutor() ? "inactive" : "active", null, null, null, branchId)}
                        createItem={null}

                        renderItem={({key, item, wasChanged, setWasChanged}) => (
                            <UserChooseItem key={key} item={item} baseUrl={"agr"}
                                            text={"Ряд #" + item.id.toString().padStart(4, "0")}/>
                        )}

                        ListFilter={({filter, setFilter}) => (
                            <ListFilter filter={filter} setFilter={setFilter}/>
                        )}

                        filterFields={
                            [
                                {name: 'id', placeholder: 'ID', label: 'ID'}
                            ]
                        }
                    />
                </div>
            }


        </PageThemplate>
    );
};

export default ChooseAgrPage;