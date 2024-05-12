import React, {useContext} from 'react';
import PageThemplate from "../../PageThemplate/PageThemplate";
import {Context} from "../../../index";
import cls from "./UserChooseItem.module.css";
import {useClassNames} from "../../../shared/lib/useClassNames";
import {useNavigate} from "react-router-dom";

const UserChooseItem = ({item, className, text, baseUrl, color}) => {
    const navigate = useNavigate();
    return (
        <div className={useClassNames(cls.chose_item, [cls[className]])} style={{backgroundColor: color}} onClick={() => navigate(`/${baseUrl}/${item.id}`)}>
            {text}
        </div>
    );
};

export default UserChooseItem;