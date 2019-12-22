import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { login } from './../../actions/securityActions';
import classnames from 'classnames';

class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
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
        
        const loginRequest = {
            username: this.state.username,
            password: this.state.password
        };

        //console.log(loginRequest);
        this.props.login(loginRequest, this.props.history);
    }

    componentWillReceiveProps(nextProps){
        console.log(nextProps);
        if(nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    render() {
        const {errors} = this.state;

        return (
            <div className="login">
                <div className="container">
                    <div className="row">
                        <div className="col-md-6 m-auto">
                            <h1 className="display-4 text-center">Log In</h1>
                            <form onSubmit={this.handleSubmit}>
                                <div className="form-group">
                                    <input 
                                        type="text" 
                                        onChange={this.handleChange}
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.username
                                        })} 
                                        placeholder="Username" name="username" />
                                    {errors.username && (
                                        <div className="invalid-feedback">{errors.username}</div>
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
                                <input type="submit" className="btn btn-info btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    
        );
    }
}

Login.propTypes = {
    login: PropTypes.func.isRequired,
    security:  PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    security: state.security,
    errors: state.errors
});

export default connect(mapStateToProps, {login})(Login);

