import * as API from './api'

export default {

  // getStoryMapRoles: storyid => {
  //   return API.GET(`storyroles/${storyid}`)
  // },

  getStoryMapList: userid => {
    return API.GET(`getStoryMapList`, { 'userId': userid })
  },

  deleteStoryMap: (storyid, params) => {
    return API.POST('deleteStoryMap', params)
  },

  updateStoryMap: params => {
    return API.POST('updateStoryMap', params)
  }
}
