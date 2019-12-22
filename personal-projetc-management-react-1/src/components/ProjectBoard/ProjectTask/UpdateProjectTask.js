import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { getProjectTask, updateProjectTask } from './../../../actions/projectTaskActions';
import classnames from 'classnames';
import { Link } from 'react-router-dom';

class UpdateProjectTask extends Component {

    constructor(props) {
        super(props);

        this.state = {
            projectTaskId: "",
            summary: "",
            projectIdentifier: "",
            projectSequence: "",
            acceptanceCriteria: "",
            dueDate: "",
            priority: 0,
            status: "",
            errors: {}
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const value  = event.target.value;
        const name   = event.target.name;

        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        event.preventDefault();

        const updateProjectTask = {
            projectTaskId: this.state.projectTaskId,
            summary: this.state.summary,
            acceptanceCriteria: this.state.acceptanceCriteria,
            dueDate: this.state.dueDate,
            priority: this.state.priority,
            status: this.state.status
        };
        //console.log(updateProjectTask);
      
        this.props.updateProjectTask(
            this.state.projectIdentifier, 
            updateProjectTask, 
            this.props.history);
    }

    componentWillMount(){
        //console.log(this.props.match);

        const {id1, id2} = this.props.match.params;
        
        this.props.getProjectTask(id1, id2, this.props.history);
    }

    componentWillReceiveProps(nextProps){
        console.log(nextProps);
        
        if(nextProps.projectTask) {
            this.updateProjectTask(nextProps.projectTask);
        }
       
        if(nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    updateProjectTask(projectTask){
        this.setState({
            projectTaskId: projectTask.projectTaskId,
            summary: projectTask.summary,
            projectIdentifier: projectTask.projectIdentifier,
            projectSequence: projectTask.projectSequence,
            acceptanceCriteria: projectTask.acceptanceCriteria,
            dueDate: projectTask.dueDate,
            priority: projectTask.priority,
            status: projectTask.status
        });
    }

    render() {
        const {errors} = this.state;

        return (
            <div className="add-PBI">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <Link to={`/projectBoard/${this.state.projectIdentifier}`} className="btn btn-warning">
                                <i className="fa fa-long-arrow-left mr-1"></i>Back to Project Board
                            </Link>
                            <h4 className="display-4 text-center">Update Project Task</h4>
                            <p className="lead text-center">
                                Project Name: {this.state.projectIdentifier} | Project Task ID: {this.state.projectSequence}
                            </p>
                            <form onSubmit={this.handleSubmit}>
                                <div className="form-group">
                                    <input 
                                        type="text"
                                        onChange={this.handleChange}  
                                        value={this.state.summary} 
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.summary
                                        })} 
                                        name="summary" placeholder="Project Task summary" />
                                    {errors.summary && (
                                        <div className="invalid-feedback">{errors.summary}</div>
                                    )}
                                </div>
                                <div className="form-group">
                                    <textarea 
                                        onChange={this.handleChange}  
                                        value={this.state.acceptanceCriteria} 
                                        className="form-control form-control-lg" 
                                        placeholder="Acceptance Criteria" name="acceptanceCriteria">

                                    </textarea>
                                </div>
                                <h5>Due Date</h5>
                                <div className="form-group">
                                    <input 
                                        type="date" 
                                        onChange={this.handleChange}  
                                        value={this.state.dueDate} 
                                        className="form-control form-control-lg" name="dueDate" />
                                </div>
                                <div className="form-group">
                                    <select 
                                        className="form-control form-control-lg" 
                                        name="priority" onChange={this.handleChange} 
                                        value={this.state.priority} >
                                        <option value={0}>Select Priority</option>
                                        <option value={1}>High</option>
                                        <option value={2}>Medium</option>
                                        <option value={3}>Low</option>
                                    </select>
                                </div>

                                <div className="form-group">
                                    <select 
                                        className="form-control form-control-lg" 
                                        name="status" 
                                        onChange={this.handleChange} 
                                        value={this.state.status}>
                                        <option value="">Select Status</option>
                                        <option value="TO_DO">TO DO</option>
                                        <option value="IN_PROGRESS">IN PROGRESS</option>
                                        <option value="DONE">DONE</option>
                                    </select>
                                </div>

                                <input type="submit" className="btn btn-primary btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

UpdateProjectTask.propTypes = {
    getProjectTask: PropTypes.func.isRequired,
    updateProjectTask: PropTypes.func.isRequired,
    projectTask: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    projectTask: state.projectTask,
    errors: state.errors
});

export default connect(mapStateToProps, {getProjectTask, updateProjectTask})(UpdateProjectTask);