import React, {useContext} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import ItemList from "../../../enities/ItemList/ItemList";
import ListFilter from "../../../components/ListFilter/ListFilter";
import {Context} from "../../../index";
import UserItem from "../../../components/Items/UserItem/UserItem";

const UsersPage = () => {
    const {store} = useContext(Context);

    return (
        <PageThemplate label={"Пользователи"}>
            <ItemList
                fetchItems={(filter) => store.users.getUsersByFilter(filter.id, filter.status, filter.username,filter.email, filter.role_id, null)}
                renderItem={({ key, item, wasChanged, setWasChanged }) => (
                    <UserItem key={key} user={item} wasChanged={wasChanged} setWasChanged={setWasChanged} />
                )}

                ListFilter={({ filter, setFilter }) => (
                    <ListFilter filter={filter} setFilter={setFilter} />
                )}

                filterFields={
                    [
                        { name: 'id', placeholder: 'ID', label: 'ID' },
                        { name: 'status', placeholder: 'Статус', label: 'статусу' },
                        { name: 'username', placeholder: 'Shiz', label: 'имени' },
                        { name: 'email', placeholder: 'shiz@mail.ru', label: 'email' },
                        { name: 'role_id', placeholder: 'ID', label: 'ID роли' }
                    ]
                }
            />
        </PageThemplate>
    );
};

export default UsersPage;