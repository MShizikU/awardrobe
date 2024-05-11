import React from 'react';
import cls from './Button.module.css';
import {useClassNames} from "../../lib/useClassNames";

export const Button = ({ className, children, action, mouseOver, mouseOut }) => {

    return (
        <button
            onClick={action}
            className={useClassNames(cls.button, [cls[className]])}
            onMouseOver={mouseOver}
            onMouseOut={mouseOut}
        >
            {children}
        </button>
    )
}

export default Button;