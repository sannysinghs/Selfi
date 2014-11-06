package com.selfi.utils;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.ListView;

public class SwipeToRemove implements OnTouchListener {
	private ListView mListView;
	private DismissCallbacks callbacks;
	int mSlop , mAnimationTime;
	float deltaX , deltaY , tabX , tabY,rawX,rawY;
	boolean isSwiping;
	int mListViewWidth = 1;
	int tabPosition;
	View tabView;
	VelocityTracker mVelocityTracker;
	
	public interface DismissCallbacks{
		void OnDismiss(int position);
	}
	
	public SwipeToRemove(ListView mListView , DismissCallbacks callbacks){
		this.mListView = mListView;
		this.callbacks = callbacks;
		ViewConfiguration album_list_config = ViewConfiguration.get(mListView.getContext());
		mSlop = album_list_config.getScaledTouchSlop();
		mAnimationTime = mListView.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime);
		mListView.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			if (mVelocityTracker == null) {
				return false;
			}
			deltaX =  tabX - rawX;
			deltaY =  tabY- rawY;
			boolean dismiss , dismissRight;
			if (isSwiping &&  Math.abs(deltaX) > mListViewWidth/2 ) {
				dismiss = true;
				dismissRight = deltaX > 0 ;  
				performDismiss(tabPosition,tabView);
			}else{
				dismiss = false;
			}
			
			if(!dismiss){
				tabView.animate().translationX(0).alpha(1).setDuration(mAnimationTime).setListener(null);
			}
			
			tabX = tabY = 0;
			tabView = null;
			tabPosition = ListView.INVALID_POSITION;
			mVelocityTracker.recycle();
			mVelocityTracker = null;
			isSwiping = false;
			break;
		case MotionEvent.ACTION_DOWN:
			if (mListViewWidth < 2) {
				mListViewWidth = mListView.getWidth();
			}
	  
		  	rawX = event.getRawX();
			rawY = event.getRawY();
			
			Rect rect = new Rect();
			int[] location =  new int[2];
			mListView.getLocationOnScreen(location);
			int x =   (int) (rawX - location[0]);
			int y = (int) (rawY - location[1]);
			int count = mListView.getChildCount();
			for (int i = 0; i < count; i++) {
				View child = mListView.getChildAt(i);
				child.getHitRect(rect);
				if (rect.contains(x,y)) {
					tabView = child;
					break;
				}
			}//end if 
			if (tabView !=null) {
				tabX = tabView.getX();
				tabY = tabView.getY();
				tabPosition = mListView.getPositionForView(tabView);
				mVelocityTracker = VelocityTracker.obtain();
				mVelocityTracker.addMovement(event);
			}else{
				tabView = null;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (mVelocityTracker == null) {
				return false;
			}
	  		//update movement coordinates
			updateTabCoords(event.getX(),event.getY());
			
			deltaX =  tabX - rawX;
			deltaY =  tabY- rawY;
			float mSwpingSlop = 0 ;
			if (Math.abs(deltaX) > mSlop) {
				isSwiping = true; 
				mSwpingSlop = deltaX - ( (deltaX > 0)? mSlop : -mSlop );
				mListView.requestDisallowInterceptTouchEvent(true);
			
			    // Cancel ListView's touch (un-highlighting the item)
                MotionEvent cancelEvent = MotionEvent.obtain(event);
                cancelEvent.setAction(MotionEvent.ACTION_CANCEL |
                        (event.getActionIndex()
                                << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                mListView.onTouchEvent(cancelEvent);
                cancelEvent.recycle();
			}
			
			if (isSwiping == true) {
				tabView.setTranslationX(mSwpingSlop);
				tabView.setAlpha(Math.max(0f, Math.min(1f,1f - 1f * Math.abs(deltaX) / mListViewWidth)));
				return true;
			}
			break;
		}
		return false;
	}

	private void performDismiss(final int pos, final View currentView) {
		// TODO Auto-generated method stub
		
		Animation animation = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime,
					Transformation t) {
				// TODO Auto-generated method stub
				if (interpolatedTime > 1) {
					currentView.setVisibility(View.GONE);
				}else{
					currentView.getLayoutParams().height = currentView.getHeight() -  (int) (currentView.getHeight() * interpolatedTime);
					currentView.requestLayout();
				}
				super.applyTransformation(interpolatedTime, t);
			}
			@Override
			public boolean willChangeBounds() {
				// TODO Auto-generated method stub
				return true;
			}
		};
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
//				albumAdapter.removeAlbum(item,pos);
				callbacks.OnDismiss(pos);
			}
		});
		animation.setDuration(200);
		currentView.startAnimation(animation);
	}

	private void updateTabCoords(float x, float y) {
		// TODO Auto-generated method stub
		tabX = x;
		tabY = y;
	}
	
	
}
