import React from 'react';
import cls from './Input.module.css'

const Input = ({
       type,
       placeholder,
       inputValue,
       setInputValue,
       disabled,
       range,
       label,
       onChange
   }) => {
    return (
        <div className={cls.inputwrapper}>
            <div className={cls.label}>{label}</div>
            <input
                className={cls.input}
                type={type}
                placeholder={placeholder}
                onChange={(e) => setInputValue(e.target.value)}
                value={inputValue}
                disabled={disabled}
                autoComplete='on'
            />
        </div>
    );
};

export default Input;