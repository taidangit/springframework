import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { getUserInfo, updateUserInfo } from './../../actions/securityActions';
import classnames from 'classnames';

class UpdateUserInfo extends Component {

    constructor(props) {
        super(props);

        this.state = {
            userId: "",
            username: "",
            fullName: "",
            email: "",
            password: "",
            newPassword: "",
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
        
        const updateUser = {
            userId: this.state.userId,
            username: this.state.username,
            fullName: this.state.fullName,
            email: this.state.email,
            password: this.state.password,
            newPassword: this.state.newPassword,
            confirmPassword: this.state.confirmPassword
        };

        //console.log(updateUser);
        this.props.updateUserInfo(updateUser, this.props.history);
    }

    componentWillMount(){
        this.props.getUserInfo();
    }

    componentWillReceiveProps(nextProps){
        //console.log(nextProps);
        
        if(nextProps.userInfo) {
            this.updateUserInfo(nextProps.userInfo);
        }
       
        if(nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    updateUserInfo(userInfo){
        this.setState({
            userId: userInfo.userId,
            username: userInfo.username,
            fullName: userInfo.fullName,
            email: userInfo.email,
            password: userInfo.password
        });
    }

    render() {
        const {errors} = this.state;

        return (
            <div className="register">
                <div className="container">
                    <div className="row">
                        <div className="col-md-6 m-auto">
                            <h1 className="display-4 text-center">Update User Info</h1>
                            <p className="lead text-center">Update your account</p>
                            <form onSubmit={this.handleSubmit}>
                                <div className="form-group">
                                    <input 
                                        type="text"
                                        onChange={this.handleChange} 
                                        value={this.state.username} 
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
                                        value={this.state.fullName} 
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
                                        value={this.state.email} 
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
                                            "is-invalid": errors.inccorectPassword

                                        })}
                                        placeholder="Current Password" name="password" />
                                    {errors.inccorectPassword && (
                                        <div className="invalid-feedback">{errors.inccorectPassword}</div>
                                    )}
                                   
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="password" 
                                        onChange={this.handleChange} 
                                        className="form-control form-control-lg"
                                        placeholder="New Password"
                                        name="newPassword" />
                                   
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="password" 
                                        onChange={this.handleChange} 
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.confirmPassword
                                        })}
                                        placeholder="Confirm Password"
                                        name="confirmPassword"  />
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
        )
    }
}

UpdateUserInfo.propTypes = {
    updateUserInfo: PropTypes.func.isRequired,
    userInfo: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    userInfo: state.userInfo,
    errors: state.errors
});

export default connect(mapStateToProps, {getUserInfo, updateUserInfo})(UpdateUserInfo);