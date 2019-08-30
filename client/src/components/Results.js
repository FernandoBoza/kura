import React from 'react';
import {haversine} from '../Utils/Utils';

const Result = (props) => {
    const hospitals = props.hospitals;
    let {lat, lng} = props;
    let sortedResult = [];
        hospitals.map( h => {
        sortedResult.push({"hospital": h ,"distance": (haversine(lat, lng,h.lat, h.lng))});
        return sortedResult.sort((a, b) => parseFloat(a.distance) - parseFloat(b.distance));
    });
    return (
        <ul className='hospital-results'>
            {sortedResult.map(h => {
                let selected = props.selected.id === h.hospital.id ? 'selected' : '';
                return (
                    <li key={h.hospital.id} className={selected} onClick={() => props.hospitalSelect(h.hospital)}>
                        <i className="fas fa-hospital"/>
                        <div>
                            <h5 className="title">{h.hospital.name}</h5>
                            <p className="address">{h.hospital.address} {h.hospital.city} {h.hospital.state} {h.hospital.zipcode}</p>
                            <p className="phone">
                                <a href={'tel:'+h.hospital.phone}>{h.hospital.phone}</a>
                            </p>
                            <p onClick={()=> props.getAllProcedures(h.hospital)} className="services-icon"><i className="fas fa-heartbeat"/> <span> Services</span></p>
                        </div>
                    </li>
                )
            })}
        </ul>
    )
};

export default Result;