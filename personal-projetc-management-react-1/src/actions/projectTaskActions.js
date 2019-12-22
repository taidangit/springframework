import axios from 'axios';
import * as types from './types';

export const createProjectTask = (projectIdentifier, projectTask, history) => async dispatch => {
    try {
        await axios.post(`http://localhost:8084/api/projectTask/${projectIdentifier}`, projectTask);
        history.push(`/projectBoard/${projectIdentifier}`);
        dispatch({
            type: types.GET_ERRORS,
            payload: {}
        });
    } catch(err) {
        dispatch({
            type: types.GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const getProjectTasks = (projectIdentifier, history) =>  async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8084/api/projectTask/${projectIdentifier}`);
        dispatch({
            type: types.GET_PROJECT_TASKS,
            payload: res.data
        });
    } catch(err) {
        dispatch({
            type: types.GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const getProjectTask = (id1, id2, history) =>  async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8084/api/projectTask/${id1}/${id2}`);
        dispatch({
            type: types.GET_PROJECT_TASK,
            payload: res.data
        });
    } catch(err) {
        history.push(`/projectBoard/${id1}`);
    }
};

export const updateProjectTask = (id1, projectTask, history) => async dispatch => {
    try {
        await axios.put(`http://localhost:8084/api/projectTask/${id1}`, projectTask);
        history.push(`/projectBoard/${id1}`);
        dispatch({
            type: types.GET_ERRORS,
            payload: {}
        });
    } catch(err) {
        dispatch({
            type: types.GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const deleteProjectTask = (id1, id2) =>  async dispatch => {
    await axios.delete(`http://localhost:8084/api/projectTask/${id1}/${id2}`);

    dispatch({
        type: types.DELETE_PROJECT_TASK,
        payload: id2
    });
};

