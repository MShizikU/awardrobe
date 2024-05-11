import type AppStore from "../AppStore";
import {makeAutoObservable} from "mobx";
import $api from "../../../http";


export default class CompanyStore{
    rootStore: AppStore;
    isLoading = false;

    constructor(rootStore: AppStore) {
        makeAutoObservable(this);
        this.rootStore = rootStore;
    }

    get isLoadingState() {
        return this.isLoading;
    }

    set isLoadingState(state) {
        this.isLoading = state;
    }

    createCompany = async (status, name, inn, p_address, l_address, manager_id) => {
        try {
            this.isLoadingState = true;
            const response = await $api.post('/company', {
                status: status,
                name: name,
                inn: inn,
                physical_address: p_address,
                legal_address: l_address,
                manager_id: manager_id,
            });
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    getFullCompany = async (id) => {
        try {
            this.isLoadingState = true;
            const response = await $api.get('/company/' + id);
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    getCompaniesByFilter = async (id, status, name, inn, p_address, l_address, manager_id) => {
        try {
            this.isLoadingState = true;
            const response = await $api.post('/companies/filter', {
                id: id,
                status: status,
                name: name,
                inn: inn,
                physical_address: p_address,
                legal_address: l_address,
                manager_id: manager_id
            });
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    getAllCompanies = async () => {
        try {
            this.isLoadingState = true;
            const response = await $api.get('/companies');
            console.log(response);
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    updateCompany = async (id, status, name, inn, p_address, l_address, manager_id) => {
        try {
            this.isLoadingState = true;
            const response= await $api.put('/company/' + id, {
                status: status,
                name: name,
                inn: inn,
                physical_address: p_address,
                legal_address: l_address,
                manager_id: manager_id
            });
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

    deleteCompany = async (id) => {
        try {
            this.isLoadingState = true;
            const response = await $api.delete("/company/" + id);
            return response.data;
        } catch (e) {
            this.rootStore.httpError(e);
        } finally {
            this.isLoadingState = false;
        }
    }

}