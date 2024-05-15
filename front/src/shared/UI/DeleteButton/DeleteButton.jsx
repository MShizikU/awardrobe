import React from 'react';
import {useClassNames} from "../../lib/useClassNames";
import cls from "./DeleteButton.module.css";

const DeleteButton = ({className, action }) => {
    return (
        <div
            className={useClassNames(cls.delete_button, [cls[className]])}
            onClick={action}
        ></div>
    );
};

export default DeleteButton;