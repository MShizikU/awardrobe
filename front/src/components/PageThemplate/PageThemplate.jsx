import React, {useContext} from 'react';
import cls from './PageThemplate.module.css';
import {observer} from "mobx-react-lite";
import {Context} from "../../index";

const PageThemplate = ({children, label}) => {
    const {store} = useContext(Context);

    return (
        <div className={cls.page_wrapper}>
            <div className={cls.page_label}>
                {label}
            </div>
            <div className={cls.page_content}>
                {children}
            </div>
            {store.loading
                ?
                <div className={cls.loading}>
                    <div className={cls.lbs_ring}>
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                    </div>
                </div>
                : <div></div>
            }

        </div>
    );
};

export default observer(PageThemplate);