import React, { useEffect, useState } from 'react'
import cls from './Form.module.css'
import { useClassNames } from '../../shared/lib/useClassNames'
import Button from '../../shared/UI/Button/Button'
import ButtonThemes from '../../shared/UI/Button/Button'
import Input from '../../shared/UI/Input/Input'

 const Form = ({ label, className, fields, basicAction, secondaryAction, actionButtonText, secondaryButtonText, hasSecondary }) => {

    const initialState = ({...fields.map((field) => field.preset ?? '')})

    const [inputsValues, setInputsValues] = useState(initialState)

    useEffect(() => {
        setInputsValues(initialState)
    }, [fields])

    const handleFormAction = async (e) => {
        e.preventDefault()
        const values = Object.values(inputsValues)
        basicAction(...values)
        setInputsValues(initialState)

    }

    const handleFormSecondaryAction = (e) => {
        e.preventDefault()
        const values = Object.values(inputsValues)
        secondaryAction(...values)
        setInputsValues(initialState)
    }

    return (
        <div className={cls.form_wrapper}>
            <div className={cls.form_label}>{label}</div>
            <form className={useClassNames(cls.form, [cls[className]])}>
                {
                    fields.map((field, index) =>
                        <Input
                            inputValue={inputsValues[index]}
                            setInputValue={(value) => setInputsValues({...inputsValues, [index]: value})}
                            key={index}
                            type={field.type}
                            placeholder={field.placeholder}
                            disabled={field.disabled}
                            label={field.label}
                            preset={field.preset}
                        />
                    )
                }
                <div className={cls.button_row}>
                    <Button
                        action={(e) => handleFormAction(e)}
                        className={"main"}
                    >
                        {actionButtonText}
                    </Button>

                    {hasSecondary} {
                    <Button
                        action={(e) => handleFormSecondaryAction(e)}
                        className={"secondary"}
                    >
                        {secondaryButtonText}
                    </Button>


                }
                </div>
            </form>
        </div>
    )
 }

export default Form;