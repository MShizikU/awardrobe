import React, {useContext, useState} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import BranchItem from "../../../components/Items/BranchItem/BranchItem";
import ListFilter from "../../../components/ListFilter/ListFilter";
import ItemList from "../../../enities/ItemList/ItemList";
import {Context} from "../../../index";
import CellItem from "../../../components/Items/CellItem/CellItem";
import Form from "../../../enities/Form/Form";
import Modal from "../../../shared/UI/Modal/Modal";
import Button from "../../../shared/UI/Button/Button";

const CellsPage = () => {
    const {store} = useContext(Context);
    const [isAddVisible, setIsAddVisible] = useState(false);
    return (
        <PageThemplate label={"Ячейки"}>
            <ItemList
                fetchItems={(filter) => store.cells.getCellsByFilter(filter.id, filter.status, null, null, filter.agr_id, null)}
                createItem={{
                    fields: [
                        {
                            type: 'text',
                            placeholder: 'active',
                            label: 'Статус'
                        },
                        {
                            type: 'text',
                            placeholder: '2',
                            label: 'ID ряда'
                        }
                    ],
                    createItem: (status, agr_id) => store.cells.createCell( status, agr_id)
                }}
                renderItem={({ key, item, wasChanged, setWasChanged }) => (
                    <CellItem key={key} cell={item} wasChanged={wasChanged} setWasChanged={setWasChanged} />
                )}

                ListFilter={({ filter, setFilter }) => (
                    <ListFilter filter={filter} setFilter={setFilter} />
                )}

                filterFields={
                    [
                        { name: 'id', placeholder: 'ID', label: 'ID' },
                        { name: 'status', placeholder: 'Статус', label: 'статусу' },
                        { name: 'agr_id', placeholder: 'Ряда', label: 'ряду' }
                    ]
                }
            />
            <Button
                action={(e) => setIsAddVisible(true)}
                className={"main"}
            >
                Добавить много
            </Button>
            <Modal
                visible={isAddVisible}
                setVisible={setIsAddVisible}
            >
                <Form
                    label={"Добавление"}
                    fields = {[
                        {
                            type: 'text',
                            placeholder: '2',
                            label: 'ID ряда'
                        },
                        {
                            type: 'text',
                            placeholder: '100',
                            label: 'Количество'
                        },
                    ]}
                    basicAction={(agr_id, amount) => {
                        store.cells.createCellsMultiple(agr_id, amount);
                    }}
                    secondaryAction={() => setIsAddVisible(false)}
                    actionButtonText={"Сохранить"}
                    secondaryButtonText={"Отменить"}
                />
            </Modal>
        </PageThemplate>
    );
};

export default CellsPage;