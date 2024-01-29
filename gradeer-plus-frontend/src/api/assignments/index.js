// import request from '@/axios'
import axios from 'axios'

export const getAllAssignments = () => {  
  // return request.get({ url: 'http://localhost:8080/assignments' })
  axios.get('http://localhost:8080/assignments').then(response => {
    console.log(response)
  })
}
