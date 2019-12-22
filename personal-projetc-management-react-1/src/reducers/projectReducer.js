import * as types from './../actions/types';

const initialState = {
    projects: []
};

export default function(state = initialState, action) {
    switch(action.type) {
        case types.GET_PROJECTS:
            return {
                projects: action.payload
            };
        case types.DELETE_PROJECT:
            return {
                projects: state.projects.filter(
                    project => project.projectIdentifier !== action.payload
                )};
        default:
            return state;
    }
}