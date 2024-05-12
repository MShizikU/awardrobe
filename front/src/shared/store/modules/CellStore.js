import type AppStore from "../AppStore";
import {makeAutoObservable} from "mobx";
import $api from "../../../http";
import {message} from "antd";


export default class CellStore{
    rootStore: AppStore;

    constructor(rootStore: AppStore) {
        makeAutoObservable(this);
        this.rootStore = rootStore;
    }

    createCell = async (status, agr_id) => await this.rootStore.performRequest($api.post('/cell', {
        status: status,
        agr_id: agr_id
    }));

    createCellsMultiple = async (agr_id, amount) => await this.rootStore.performRequest($api.post('/cell', {
        agr_id: agr_id,
        amount: amount
    }));

    getFullCell = async (id) => await this.rootStore.performRequest($api.get(`/cells/${id}`));

    getCells = async () =>  await this.rootStore.performRequest($api.get(`/cells`));

    getCellsByFilter = async(id, status, sequence_number, user_id, agr_id) =>
        await this.rootStore.performRequest($api.post(`/cells/filter`, {
            id : id,
            status : status,
            sequence_number : sequence_number,
            user_id: user_id,
            agr_id: agr_id
        }));

    updateCell = async( id, status, agr_id) =>
        await this.rootStore.performRequest($api.put(`/cell/${id}`, {
            status : status,
            agr_id: agr_id,
        }));

    setUserInCell = async(user_id,agr_id ) =>
        await this.rootStore.performRequest($api.put(`/cells/set?userId=${user_id}&agrId=${agr_id}`));

    removeUserInCell = async(user_id ) =>
        await this.rootStore.performRequest($api.put(`/cells/remove?userId=${user_id}`));

    deleteCell = async(id) => await this.rootStore.performRequest($api.delete(`/cell/${id}`));

    getCurrentCell = async (id) => {
        await $api.post(`/cell/user`);
    }

}