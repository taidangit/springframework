import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { createProjectTask } from './../../../actions/projectTaskActions';
import classnames from 'classnames';

class AddProjectTask extends Component {

    constructor(props) {
        super(props);
        const {id} = this.props.match.params;

        this.state = {
            summary: "",
            acceptanceCriteria: "",
            dueDate: "",
            priority: 0,
            status: "",
            projectIdentifier: id,
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
        
        const newProjectTask = {
            summary: this.state.summary,
            acceptanceCriteria: this.state.acceptanceCriteria,
            dueDate: this.state.dueDate,
            priority: this.state.priority,
            status: this.state.status
        };

       //console.log(newProjectTask);
        this.props.createProjectTask(this.state.projectIdentifier, newProjectTask, this.props.history);
    }

    componentWillReceiveProps(nextProps){
        //console.log(nextProps);
        if(nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    render() {
        const {id} = this.props.match.params;
        const {errors} = this.state;

        return (
            <div className="add-PBI">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <Link to={`/projectBoard/${id}`} className="btn btn-warning">
                                <i className="fa fa-long-arrow-left mr-1"></i>Back to Project Board
                            </Link>
                            <h4 className="display-4 text-center">Add Project Task</h4>
                            <p className="lead text-center">Project Name + Project Code</p>
                            <form onSubmit={this.handleSubmit}>
                                <div className="form-group">
                                    <input 
                                        type="text"
                                        onChange={this.handleChange}  
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.summary
                                        })} 
                                        name="summary" placeholder="Project Task summary" />
                                    {errors.summary && (
                                        <div className="invalid-feedback">{errors.summary}</div>
                                    )}
                                </div>
                                <div className="form-group">
                                    <textarea onChange={this.handleChange}   
                                        className="form-control form-control-lg" 
                                        placeholder="Acceptance Criteria" name="acceptanceCriteria">

                                    </textarea>
                                </div>
                                <h5>Due Date</h5>
                                <div className="form-group">
                                    <input 
                                        type="date" 
                                        onChange={this.handleChange}  
                                        className="form-control form-control-lg" name="dueDate" />
                                </div>
                                <div className="form-group">
                                    <select 
                                        className="form-control form-control-lg" 
                                        name="priority" onChange={this.handleChange}>
                                        <option value={0}>Select Priority</option>
                                        <option value={1}>High</option>
                                        <option value={2}>Medium</option>
                                        <option value={3}>Low</option>
                                    </select>
                                </div>

                                <div className="form-group">
                                    <select 
                                        className="form-control form-control-lg" 
                                        name="status" onChange={this.handleChange}>
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

AddProjectTask.propTypes = {
    createProjectTask: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    errors: state.errors
});

export default connect(mapStateToProps, {createProjectTask})(AddProjectTask);