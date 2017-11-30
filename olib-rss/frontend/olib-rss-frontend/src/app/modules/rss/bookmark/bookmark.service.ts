import { Injectable }         from '@angular/core';
import { Headers, Http }      from '@angular/http';

import { Bookmark }           from './bookmark.model';

@Injectable()
export class BookmarkService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private apiUrl = '/api/bookmark';

  private bookmarkList: Bookmark[]= [];

  constructor(public http: Http) { }

  getBookmarkList(){
    return this.bookmarkList;
  }
  setBookmarkList(bookmarkList : Bookmark[]){
    this.bookmarkList = bookmarkList;
  }
  
  loadBookmark() : Promise<Bookmark[]>{
    return this.http.get(this.apiUrl)
              .toPromise()
              .then(response => response.json() as Bookmark[])
              .catch(this.handleError);
  }

  saveBookmark(bookmark : Bookmark) : Promise<number>{
    return this.http.post(this.apiUrl,JSON.stringify(bookmark),{headers: this.headers})
              .toPromise()
              .then(response => response.json() as number)
              .catch(this.handleError); 
  }

  deleteBookmark(id:number) : Promise<Boolean>{
    return this.http.delete(this.apiUrl+"/"+id)
              .toPromise()
              .then(response => response.json() as Boolean)
              .catch(this.handleError);
  }

  updateBookmark(bookmark : Bookmark) : Promise<number>{
    return this.http.put(this.apiUrl,JSON.stringify(bookmark),{headers: this.headers})
              .toPromise()
              .then(response => response.json() as number)
              .catch(this.handleError); 
  }


  private handleError(error: any): Promise<any> {
    return null;
  }

}
