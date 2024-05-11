import React, {useContext} from 'react';
import {Context} from "../index";
import { Route, Routes} from "react-router-dom";
import {adminRoutes, executorRoutes, publicRoutes, userRoutes} from '../router';
import Login from "../pages/utlis/Login";
import CompaniesPage from "../pages/admin/CompaniesPage/CompaniesPage";
import UserPage from "../pages/utlis/UserPage/UserPage";
import {observer} from "mobx-react-lite";

const AppRouter = () => {
    const {store} = useContext(Context);

    return (
        store.isAuth
            ?
                store.isAdmin()
                    ? <Routes>
                        {adminRoutes.map(route =>
                            <Route
                                element={route.element}
                                path={route.path}
                                exact={route.exact}
                                key={route.path}
                            />
                        )}
                        <Route path="*" element={<CompaniesPage/>}  />
                     </Routes>
                    :
                store.isExecutor()
                ?
                    <Routes>
                        {executorRoutes.map(route =>
                            <Route
                                element={route.element}
                                path={route.path}
                                exact={route.exact}
                                key={route.path}
                            />
                        )}
                        <Route path="*" element={<UserPage/>}  />
                    </Routes>
                :
                    <Routes>
                        {userRoutes.map(route =>
                            <Route
                                element={route.element}
                                path={route.path}
                                exact={route.exact}
                                key={route.path}
                            />
                        )}
                        <Route path="*" element={<UserPage/>}  />
                    </Routes>
            :
            <Routes>
                {publicRoutes.map(route =>
                    <Route
                        element={route.element}
                        path={route.path}
                        exact={route.exact}
                        key={route.path}
                    />
                )}
                <Route path="*" element={<Login/>}  />
            </Routes>
    );
};

export default observer(AppRouter);