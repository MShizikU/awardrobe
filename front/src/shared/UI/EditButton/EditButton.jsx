import React from 'react';
import {useClassNames} from "../../lib/useClassNames";
import cls from "./EditButton.module.css";

const EditButton = ({className, action }) => {
    return (
        <div
            className={useClassNames(cls.edit_button, [cls[className]])}
            onClick={action}
        ></div>
    );
};

export default EditButton;