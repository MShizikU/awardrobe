import React, {useEffect, useState} from 'react';
import ListFilter from "../../components/ListFilter/ListFilter";
import Button from "../../shared/UI/Button/Button";
import Modal from "../../shared/UI/Modal/Modal";
import Form from "../Form/Form";
import cls from "./ItemList.module.css";

const ItemList = ({ fetchItems, createItem, renderItem, handleAddItem }) => {

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
    }, [wasChanged, filter]);

    return (
        <div className={cls.list_wrapper}>
            <div className="filter_wrapper">
                <ListFilter filter={filter} setFilter={setFilter}/> {/* Добавляем компонент фильтра */}
            </div>
            <div className={cls.items_wrapper}>
                {itemsList.map(item =>
                    renderItem({key: item.id, item, wasChanged, setWasChanged})
                )}
            </div>
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
                    basicAction={handleAddItem}
                    secondaryAction={() => setIsAddVisible(false)}
                    actionButtonText={"Сохранить"}
                    secondaryButtonText={"Отменить"}
                />
            </Modal>
        </div>
    );
};

export default ItemList;