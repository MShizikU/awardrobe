import React from 'react';
import cls from "./VisitItem.module.css";

const VisitItem = ({visit, wasChanged, setWasChanged}) => {
    const id = visit.id + "";
    return (
        <div className={cls.visit_item}>
            <div className={cls.visit_id}>
                Посещение #{id.padStart(4, "0")}
            </div>
            <div className={cls.visit_time}>
                <div>
                    С {visit.start_time}
                </div>
                <div>
                    По {visit.end_time}
                </div>
            </div>
            <div className={cls.sub_data}>
                <div>
                    Ячейка: {visit.cell_id}
                </div>
                <div>
                    Пользователь: {visit.user_id}
                </div>
            </div>
        </div>
    );
};

export default VisitItem;