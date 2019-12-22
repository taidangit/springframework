import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { deleteProjectTask } from './../../../actions/projectTaskActions';

class ProjectTask extends Component {

    constructor(props) {
        super(props);

        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete(id1, id2){
        this.props.deleteProjectTask(id1, id2);
    }

    render() {
        const {projectTask} = this.props;
        let priorityString = "";
        let priorityClass = "";

        if(projectTask.priority === 1) {
            priorityClass = "bg-danger text-light";
            priorityString = "HIGH";
        } else if(projectTask.priority === 2) {
            priorityClass = "bg-warning text-light";
            priorityString = "MEDIUM";
        } else {
            priorityClass = "bg-info text-light";
            priorityString = "LOW";
        }

        return (
            <div className="card mb-1 bg-light">

                <div className={`card-header ${priorityClass}`}>
                    ID: {projectTask.projectSequence} -- Priority: {priorityString}
                </div>
                <div className="card-body bg-light">
                    <h5 className="card-title">{projectTask.summary}</h5>
                    <p className="card-text text-truncate ">
                        {projectTask.acceptanceCriteria}
                    </p>
                    <Link to={`/viewProjectTask/${projectTask.projectIdentifier}/${projectTask.projectSequence}`}
                         className="btn btn-primary">
                        <i className="fa fa-check"></i>View
                    </Link>

                    <Link to={`/updateProjectTask/${projectTask.projectIdentifier}/${projectTask.projectSequence}`} 
                        className="btn btn-warning ml-2">
                        <i className="fa fa-pencil"></i>Update
                    </Link>

                    <button className="btn btn-danger ml-2"
                         onClick={() => {if(window.confirm('Are you sure you want to delete this project task?')){this.handleDelete(projectTask.projectIdentifier, projectTask.projectSequence)};}}>
                        <i className="fa fa-times"></i>Delete
                    </button>
                </div>
            </div>
        )
    }
}

ProjectTask.propTypes = {
    deleteProjectTask: PropTypes.func.isRequired
}

export default connect(null, {deleteProjectTask})(ProjectTask);