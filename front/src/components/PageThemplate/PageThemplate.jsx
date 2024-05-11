import React from 'react';
import cls from './PageThemplate.module.css';

const PageThemplate = ({children, label}) => {
    return (
        <div className={cls.page_wrapper}>
            <div className={cls.page_label}>
                {label}
            </div>
            <div className={cls.page_content}>
                {children}
            </div>
        </div>
    );
};

export default PageThemplate;