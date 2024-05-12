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

    createCompany = async (status, name, inn, p_address, l_address, manager_id) => await this.rootStore.performRequest($api.post('/company', {
        status: status,
        name: name,
        inn: inn,
        physical_address: p_address,
        legal_address: l_address,
        manager_id: manager_id,
    }), true);

    getFullCompany = async (id) => await this.rootStore.performRequest($api.get('/company/' + id));

    getCompaniesByFilter = async (id, status, name, inn, p_address, l_address, manager_id) => await this.rootStore.performRequest($api.post('/companies/filter', {
        id: id,
        status: status,
        name: name,
        inn: inn,
        physical_address: p_address,
        legal_address: l_address,
        manager_id: manager_id
    }));

    getAllCompanies = async () => await this.rootStore.performRequest($api.get('/companies'));

    updateCompany = async (id, status, name, inn, p_address, l_address, manager_id) => await this.rootStore.performRequest($api.put('/company/' + id, {
        status: status,
        name: name,
        inn: inn,
        physical_address: p_address,
        legal_address: l_address,
        manager_id: manager_id
    }), true);

    deleteCompany = async (id) => await this.rootStore.performRequest($api.delete("/company/" + id), true);

}