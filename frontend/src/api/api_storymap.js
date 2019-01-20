import * as API from './api'

export default {

  // getStoryMapRoles: storyid => {
  //   return API.GET(`storyroles/${storyid}`)
  // },

  addStoryMap: story => {
    return API.POST('addStoryMap', { 'storyName': story.storyName,
      'storyDescription': story.storyDescription,
      'release': story.release,
      'userId': story.userId,
      'storyId': story.storyId })
    // return API.POST('addStoryMap', { 'storyName': story.storyName,
    //   'storyDescription': story.storyDescription,
    //   'release': story.release,
    //   'userId': story.userId,
    //   'storyId': story.storyId })
  },

  getStoryMapList: userid => {
    return API.GET(`getStoryMapList`, { 'userId': userid })
  },

  deleteStoryMap: params => {
    return API.POST('deleteStoryMap', params)
  },

  updateStoryMap: params => {
    return API.POST('updateStoryMap', params)
  }
}
