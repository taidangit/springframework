import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { getProjectTask } from './../../../actions/projectTaskActions';

class ViewProjectTask extends Component {

    componentWillMount(){
        //console.log(this.props.match);

        const {id1, id2} = this.props.match.params;
        this.props.getProjectTask(id1, id2, this.props.history);
    }

    render() {
        const {id1} = this.props.match.params;
        const {projectTask} = this.props;

        return (
            <div className="add-PBI">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <Link to={`/projectBoard/${id1}`} className="btn btn-warning">
                                <i className="fa fa-long-arrow-left mr-1"></i>Back to Project Board
                            </Link>
                            <h4 className="display-4 text-center">Project Task Detail</h4>
                            
                            <p><strong>Project Task Id: </strong>{projectTask.projectSequence}</p>
                            <p><strong>Project Task Name: </strong>{projectTask.projectIdentifier}</p>
                            <p><strong>Summary: </strong>{projectTask.summary}</p>
                            <p><strong>Acceptance Criteria: </strong>{projectTask.acceptanceCriteria}</p>
                            <p><strong>Due Date: </strong>{projectTask.dueDate}</p>
                            <p><strong>Priority: </strong>{projectTask.priority}</p>
                            <p><strong>Status: </strong>{projectTask.status}</p>
                           
                        </div>
                    </div>
                </div>
            </div>
    
        );
    }
}

ViewProjectTask.propTypes = {
    getProjectTask: PropTypes.func.isRequired,
    projectTask: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    projectTask: state.projectTask
});

export default connect(mapStateToProps, {getProjectTask})(ViewProjectTask);

