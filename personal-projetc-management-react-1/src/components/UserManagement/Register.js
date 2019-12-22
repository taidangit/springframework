import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { createUser } from './../../actions/securityActions';
import classnames from 'classnames';

class Register extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            fullName: "",
            email: "",
            password: "",
            confirmPassword: "",
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
        
        const newUser = {
            username: this.state.username,
            fullName: this.state.fullName,
            email: this.state.email,
            password: this.state.password,
            confirmPassword: this.state.confirmPassword
        };

        //console.log(newUser);
        this.props.createUser(newUser, this.props.history);
    }

    componentWillReceiveProps(nextProps){
        //console.log(nextProps);
        if(nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    render() {
        const {errors} = this.state;
        
        return (
            <div className="register">
                <div className="container">
                    <div className="row">
                        <div className="col-md-6 m-auto">
                            <h1 className="display-4 text-center">Sign Up</h1>
                            <p className="lead text-center">Create your account</p>
                            <form onSubmit={this.handleSubmit}>
                                <div className="form-group">
                                    <input 
                                        type="text"
                                        onChange={this.handleChange}  
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.username
                                        })} 
                                        placeholder="Username" name="username"
                                         />
                                    {errors.username && (
                                        <div className="invalid-feedback">{errors.username}</div>
                                    )}
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="text"
                                        onChange={this.handleChange}  
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.fullName
                                        })} 
                                        placeholder="Full name" name="fullName"
                                         />
                                    {errors.fullName && (
                                        <div className="invalid-feedback">{errors.fullName}</div>
                                    )}
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="text"
                                        onChange={this.handleChange} 
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.email
                                        })}  
                                        placeholder="Email Address" name="email" />
                                    {errors.email && (
                                        <div className="invalid-feedback">{errors.email}</div>
                                    )}
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="password"
                                        onChange={this.handleChange}  
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.password
                                        })}
                                        placeholder="Password" name="password" />
                                    {errors.password && (
                                        <div className="invalid-feedback">{errors.password}</div>
                                    )}
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="password" 
                                        onChange={this.handleChange} 
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.confirmPassword
                                        })}
                                        placeholder="Confirm Password"
                                        name="confirmPassword" />
                                    {errors.confirmPassword && (
                                        <div className="invalid-feedback">{errors.confirmPassword}</div>
                                    )}
                                </div>
                                <input type="submit" className="btn btn-info btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    
        );
    }
}

Register.propTypes = {
    createUser: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    errors: state.errors
});

export default connect(mapStateToProps, {createUser})(Register);

