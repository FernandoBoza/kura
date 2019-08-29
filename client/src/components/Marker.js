import React from 'react';

const Marker = (props) => {
    const {name, phone, address, id} = props.hosp;
    const selected = props.selected.id === id ? 'selected' : '';
    return (
        <div className={'marker ' + selected} onClick={() => props.hospitalSelect(props.hosp)}>
            <i className="fas fa-hospital"/>

            <div className="hosp-content">
                <h4>{name}</h4>
                <a href={'tel:' + phone}>{phone}</a>
                <p>{address}</p>
            </div>
        </div>
    )
};

export default Marker