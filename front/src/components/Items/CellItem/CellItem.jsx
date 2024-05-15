import React, {useContext} from 'react';
import cls from "./CellItem.module.css";
import {Context} from "../../../index";
import Form from "../../../enities/Form/Form";
import Modal from "../../../shared/UI/Modal/Modal";

const CellItem = ({cell, wasChanged, setWasChanged}) => {

    const [isEditVisible, setIsEditVisible] = React.useState(false);
    const {store} = useContext(Context);
    const seq_number = cell.sequence_number + "";
    const status = cell.status;
    const color = (status === "active" ? "#60d394" : status === "inactive" ? "#ffd97d" : status === "inuse" ? "gray" : "#ee6055");
    return (
        <div className={cls.cell_item}>
            <div className={cls.cell_folder} style={{background: color}} onClick={() => {setIsEditVisible(true)}} >
                #{seq_number.padStart(4, "0")}
            </div>
            <Modal
                visible={isEditVisible}
                setVisible={setIsEditVisible}
            >
                <Form
                    label={"Обновление ячейки"}
                    fields={[
                        {
                            type: 'text',
                            placeholder: 'active',
                            label: 'Статус',
                            preset: cell.status
                        },
                        {
                            type: 'text',
                            placeholder: '2',
                            label: 'ID ряда',
                            preset: cell.agr_id
                        }
                    ]}
                    basicAction={async (status, agr_id) => {
                        store.cells.updateCell(cell.id, status, agr_id).then(() => {
                            setWasChanged(!wasChanged)
                        });
                        setIsEditVisible(false);
                    }}
                    secondaryAction={(username, password) => {
                        store.cells.deleteCell(cell.id).then(() => {
                            setWasChanged(!wasChanged)
                        });
                        setIsEditVisible(false);
                    }}
                    actionButtonText={"Сохранить"}
                    secondaryButtonText={"Удалить"}
                ></Form>
                <div className={cls.cell_sub_data}>
                    <div>
                        ID: {cell.id}
                    </div>
                    <div>
                        Пользователь: {cell.user_id}
                    </div>
                    <div>
                        Ряд: {cell.agr_id}
                    </div>
                </div>
            </Modal>
        </div>


    );
};

export default CellItem;