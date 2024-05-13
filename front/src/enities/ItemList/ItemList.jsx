import React, {useContext, useEffect, useState} from 'react';
import ListFilter from "../../components/ListFilter/ListFilter";
import Button from "../../shared/UI/Button/Button";
import Modal from "../../shared/UI/Modal/Modal";
import Form from "../Form/Form";
import cls from "./ItemList.module.css";
import {Context} from "../../index";
import {observer} from "mobx-react-lite";

const ItemList = ({ fetchItems, createItem, renderItem, filterFields}) => {
    const {store} = useContext(Context);
    const [itemsList, setItemsList] = useState([]);
    const [wasChanged, setWasChanged] = useState(false);
    const [isAddVisible, setIsAddVisible] = useState(false);
    const [filter, setFilter] = useState({ id: '', status: '' });

    const fetchData = async () => {
        const items = await fetchItems(filter); // Передаем фильтр в функцию загрузки данных
        setItemsList(items);
    };

    useEffect(() => {
        fetchData();
        setIsAddVisible(false);
    }, [store.hasUpdate, wasChanged, filter]);

    return (
        <div className={cls.list_wrapper}>
            <div className="filter_wrapper">
                <ListFilter
                    filterFields={filterFields}
                    filter={filter}
                    setFilter={setFilter}
                />
            </div>
            <div className={cls.items_wrapper}>
                {itemsList.map(item =>
                    renderItem({key: item.id, item, wasChanged, setWasChanged})
                )}
            </div>
            {createItem != null
            ?
                <div>
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
                            label={"Добавление"}
                            fields={createItem.fields}
                            basicAction={(...values) => {
                                createItem.createItem(...values);
                            }
                            }
                            secondaryAction={() => setIsAddVisible(false)}
                            actionButtonText={"Сохранить"}
                            secondaryButtonText={"Отменить"}
                        />
                    </Modal>
                </div>
                : <div></div>
            }


        </div>
    );
};

export default observer(ItemList);