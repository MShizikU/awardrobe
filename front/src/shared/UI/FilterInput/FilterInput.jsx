import React from 'react';
import cls from './FilterInput.module.css'

const FilterInput = ({
                   type,
                   placeholder,
                   disabled,
                   label,
                    value,
                   onChange
               }) => {
    return (
        <div className={cls.inputwrapper}>
            <div className={cls.label}>{label}</div>
            <input
                className={cls.input}
                type={type}
                placeholder={placeholder}
                onChange={onChange}
                value={value}
                disabled={disabled}
                autoComplete='on'
            />
        </div>
    );
};

export default FilterInput;