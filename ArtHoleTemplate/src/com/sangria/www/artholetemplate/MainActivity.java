
package com.sangria.www.artholetemplate;



import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	//for tabs
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    ViewPager mViewPager;
    
    //for drawer
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;

    CharSequence mDrawerTitle;
    CharSequence mTitle;
    String[] mMenuTitles;
    
    int layout_type;

    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        //choose tab or menu layout via commenting
        //Remember to remove the set of methods/classes corresponding to the other option
        
        
        setMenuLayout(savedInstanceState);
        layout_type = 1;
        
        
        //setTabLayout(savedInstanceState);
        //layout_type = 2;
        
        
    }
    
    
    //These methods/classes are for the menu layout
        
        public void setMenuLayout(Bundle savedInstanceState) {
        	
        	setContentView(R.layout.activity_main_drawer);

            mTitle = mDrawerTitle = getTitle();
            mMenuTitles = getMenuTitles();
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerList = (ListView) findViewById(R.id.left_drawer);

            // set a custom shadow that overlays the main content when the drawer opens
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
            // set up the drawer's list view with items and click listener
            mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                    R.layout.drawer_list_item, mMenuTitles));
            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

            // enable ActionBar app icon to behave as action to toggle nav drawer
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);

            // ActionBarDrawerToggle ties together the the proper interactions
            // between the sliding drawer and the action bar app icon
            mDrawerToggle = new ActionBarDrawerToggle(
                    this,                  /* host Activity */
                    mDrawerLayout,         /* DrawerLayout object */
                    R.drawable.defaultimage,  /* nav drawer image to replace 'Up' caret */
                    R.string.home_title,  /* "open drawer" description for accessibility */
                    R.string.home_title  /* "close drawer" description for accessibility */
                    ) {
                public void onDrawerClosed(View view) {
                	//uncomment to maintain main title on actionbar
                    //getActionBar().setTitle(mTitle);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }

                public void onDrawerOpened(View drawerView) {
                	//uncomment if maintaining main title on actionbar
                    //getActionBar().setTitle(mDrawerTitle);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }
            };
            mDrawerLayout.setDrawerListener(mDrawerToggle);

            if (savedInstanceState == null) {
                selectItem(0);
            }
        	
        }
        
        public String[] getMenuTitles() {
        	
        	return new String[] {getString(R.string.section_1_title),
        			getString(R.string.section_2_title),
        			getString(R.string.section_3_title),
        			getString(R.string.section_4_title),
        			getString(R.string.section_5_title)};
        
        }
        

        private class DrawerItemClickListener implements ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        }
        
        private void selectItem(int position) {
        	switch (position) {
            case 0:
            	
            	getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new HomeSectionFragment()).commit();
            	
            	mDrawerList.setItemChecked(position, true);
                setTitle(mMenuTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
                
                return;
                
            case 1:
            	getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new StorySectionFragment()).commit();
            	
            	mDrawerList.setItemChecked(position, true);
                setTitle(mMenuTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
                
                return;
            	
            case 2:
            	getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new ImageSectionFragment()).commit();
            	
            	mDrawerList.setItemChecked(position, true);
                setTitle(mMenuTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
                
                return;
            	
            case 3:
            	getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new BioSectionFragment()).commit();
            	
            	mDrawerList.setItemChecked(position, true);
                setTitle(mMenuTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
                
                return;
            	
            case 4:
            	getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new EmailSectionFragment()).commit();
            	
            	mDrawerList.setItemChecked(position, true);
                setTitle(mMenuTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
                
                return;

            default:
            	getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new ImageSectionFragment()).commit();
            	
            	mDrawerList.setItemChecked(position, true);
                setTitle(mMenuTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
                
                return;
        }

        }
       
    
    //These methods/classes are for the tab layout
    
    public void setTabLayout(Bundle savedInstanceState) {
    	
    setContentView(R.layout.activity_main_tabs);
    	
    // Create the adapter that will return a fragment for each of the three primary sections
    // of the app.
    mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

    // Set up the action bar.
    final ActionBar actionBar = getActionBar();

    // Specify that the Home/Up button should not be enabled, since there is no hierarchical
    // parent.
    actionBar.setHomeButtonEnabled(false);

    // Specify that we will be displaying tabs in the action bar.
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    // Set up the ViewPager, attaching the adapter and setting up a listener for when the
    // user swipes between sections.
    mViewPager = (ViewPager) findViewById(R.id.pager);
    mViewPager.setAdapter(mAppSectionsPagerAdapter);
    mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            // When swiping between different app sections, select the corresponding tab.
            // We can also use ActionBar.Tab#select() to do this if we have a reference to the
            // Tab.
            actionBar.setSelectedNavigationItem(position);
        }
    });

    // For each of the sections in the app, add a tab to the action bar.
    for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
        // Create a tab with text corresponding to the page title defined by the adapter.
        // Also specify this Activity object, which implements the TabListener interface, as the
        // listener for when this tab is selected.
        actionBar.addTab(
                actionBar.newTab()
                        .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                        .setTabListener(this));
    }
    
}

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
    	int position = tab.getPosition();
        mViewPager.setCurrentItem(position);
        setTitle(mAppSectionsPagerAdapter.getPageTitle(position));
        
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new HomeSectionFragment();
                    
                case 1:
                	return new StorySectionFragment();
                	
                case 2:
                	return new ImageSectionFragment();
                	
                case 3:
                	return new BioSectionFragment();
                	
                case 4:
                	return new EmailSectionFragment();

                default:
                	return new HomeSectionFragment();
            }
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
        	switch(position) {
        		case 0:
        			return getString(R.string.section_1_title);
        			
        		case 1:
        			return getString(R.string.section_2_title);
        			
        		case 2:
        			return getString(R.string.section_3_title);
        			
        		case 3:
        			return getString(R.string.section_4_title);
        			
        		case 4:
        			return getString(R.string.section_5_title);
        			
        		default:
        			return getString(R.string.section_1_title);
        				
        			
        	}
        		
        }
    }
    
  
    
    
    
    
    //These methods/classes are included in both
    
    
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
    	int position;
    	
    	if (layout_type == 1)
    	{
    		if (mDrawerToggle.onOptionsItemSelected(item)) {
    			return true;
    		}

    		// Handle action buttons
    		switch(item.getItemId()) {
    		case R.id.menu_home:

    			position = 0;

    			getSupportFragmentManager().beginTransaction()
    			.replace(R.id.content_frame, new HomeSectionFragment()).commit();

    			mDrawerList.setItemChecked(position, true);
    			setTitle(mMenuTitles[position]);
    			mDrawerLayout.closeDrawer(mDrawerList);

    			return true;

    		case R.id.menu_story:

    			position = 1;

    			getSupportFragmentManager().beginTransaction()
    			.replace(R.id.content_frame, new StorySectionFragment()).commit();

    			mDrawerList.setItemChecked(position, true);
    			setTitle(mMenuTitles[position]);
    			mDrawerLayout.closeDrawer(mDrawerList);

    			return true;

    		case R.id.menu_image:

    			position = 2;

    			getSupportFragmentManager().beginTransaction()
    			.replace(R.id.content_frame, new ImageSectionFragment()).commit();

    			mDrawerList.setItemChecked(position, true);
    			setTitle(mMenuTitles[position]);
    			mDrawerLayout.closeDrawer(mDrawerList);

    			return true;

    		case R.id.menu_bio:

    			position = 3;

    			getSupportFragmentManager().beginTransaction()
    			.replace(R.id.content_frame, new BioSectionFragment()).commit();

    			mDrawerList.setItemChecked(position, true);
    			setTitle(mMenuTitles[position]);
    			mDrawerLayout.closeDrawer(mDrawerList);

    			return true;

    		case R.id.menu_email:

    			position = 4;

    			getSupportFragmentManager().beginTransaction()
    			.replace(R.id.content_frame, new EmailSectionFragment()).commit();

    			mDrawerList.setItemChecked(position, true);
    			setTitle(mMenuTitles[position]);
    			mDrawerLayout.closeDrawer(mDrawerList);

    			return true;

    		default:
    			return super.onOptionsItemSelected(item);

    		}
    	}

    	else {
    		// Handle action buttons
    		switch(item.getItemId()) {
    		case R.id.menu_home:

    			position = 0;

    			 mViewPager.setCurrentItem(position);
    			 
    			 setTitle(getString(R.string.section_1_title));

    			return true;

    		case R.id.menu_story:

    			position = 1;

    			mViewPager.setCurrentItem(position);
    			
    			setTitle(getString(R.string.section_2_title));

    			return true;
    			

    		case R.id.menu_image:

    			position = 2;

    			mViewPager.setCurrentItem(position);
    			
    			setTitle(getString(R.string.section_3_title));

    			return true;

    		case R.id.menu_bio:

    			position = 3;

    			mViewPager.setCurrentItem(position);
    			
    			setTitle(getString(R.string.section_4_title));

    			return true;

    		case R.id.menu_email:

    			position = 4;

    			mViewPager.setCurrentItem(position);
    			
    			setTitle(getString(R.string.section_5_title));

    			return true;

    		default:
    			
    			return super.onOptionsItemSelected(item);

    		}
    		
    	}
    }
    
    
    public void sendEmail(View view){
    	
    	final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

    	emailIntent.setType("text/plain");
    	emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{getString(R.string.email_address)});
    	emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
    	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.email_body));

    	this.startActivity(Intent.createChooser(emailIntent, getString(R.string.email_chooser)));
    	
    }


    public static class HomeSectionFragment extends Fragment {
    	
    	public HomeSectionFragment(){
    		
    	}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
            View rootView = inflater.inflate(R.layout.fragment_section_home, container, false);
            
            //set text/image fields
    		TextView titleText = (TextView)rootView.findViewById(R.id.home_title);
    		titleText.setText(getString(R.string.home_title));
    		
    		TextView firstText = (TextView)rootView.findViewById(R.id.home_main_text);
    		firstText.setText(getString(R.string.home_main_text));

    		ImageView firstImage = (ImageView)rootView.findViewById(R.id.home_image_1);
    		firstImage.setImageDrawable(getResources().getDrawable(R.drawable.defaultimage));
    		firstImage.setContentDescription(getString(R.string.default_image_text));
            
            return rootView;
        }
    }
    
    public static class StorySectionFragment extends Fragment {
    	
    	public StorySectionFragment(){
    		
    	}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_story, container, false);
            
            //set text/image fields
    		TextView titleText = (TextView)rootView.findViewById(R.id.story_title);
    		titleText.setText(getString(R.string.story_title));
    		
    		TextView firstText = (TextView)rootView.findViewById(R.id.story_main_text);
    		firstText.setText(getString(R.string.story_main_text));
    		
            return rootView;
        }
    }
    
    public static class ImageSectionFragment extends Fragment {
    	
    	public ImageSectionFragment(){
    		
    	}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_image, container, false);
            
          //set text/image fields
    		TextView titleText = (TextView)rootView.findViewById(R.id.image_title);
    		titleText.setText(getString(R.string.image_title));

    		ImageView firstImage = (ImageView)rootView.findViewById(R.id.image_image_1);
    		firstImage.setImageDrawable(getResources().getDrawable(R.drawable.defaultimage));
    		firstImage.setContentDescription(getString(R.string.default_image_text));

            return rootView;
        }
    }
    
    public static class BioSectionFragment extends Fragment {
    	
    	public BioSectionFragment(){
    		
    	}

    	
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_bio, container, false);
            
    		//set text/image fields
    		TextView titleText = (TextView)rootView.findViewById(R.id.bio_title);
    		titleText.setText(getString(R.string.bio_title));
    		
    		TextView firstText = (TextView)rootView.findViewById(R.id.bio_first_text);
    		firstText.setText(getString(R.string.bio_first_text));
    		
    		TextView secondText = (TextView)rootView.findViewById(R.id.bio_second_text);
    		secondText.setText(getString(R.string.bio_second_text));
    		
    		TextView thirdText = (TextView)rootView.findViewById(R.id.bio_third_text);
    		thirdText.setText(getString(R.string.bio_third_text));
    		
    		ImageView firstImage = (ImageView)rootView.findViewById(R.id.bio_image_1);
    		firstImage.setImageDrawable(getResources().getDrawable(R.drawable.defaultimage));
    		firstImage.setContentDescription(getString(R.string.default_image_text));
    			
    		return rootView;
    	}

    }
    
    
    public static class EmailSectionFragment extends Fragment {

    	
    	public EmailSectionFragment(){
    		
    	}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_email, container, false);
            
          //set text/image fields
    		TextView titleText = (TextView)rootView.findViewById(R.id.email_title);
    		titleText.setText(getString(R.string.email_title));

    		ImageView firstImage = (ImageView)rootView.findViewById(R.id.email_image_1);
    		firstImage.setImageDrawable(getResources().getDrawable(R.drawable.defaultimage));
    		firstImage.setContentDescription(getString(R.string.default_image_text));
    		
    		ImageView secondImage = (ImageView)rootView.findViewById(R.id.email_image_2);
    		secondImage.setImageDrawable(getResources().getDrawable(R.drawable.defaultimage));
    		secondImage.setContentDescription(getString(R.string.default_image_text));
    		
    		ImageView thirdImage = (ImageView)rootView.findViewById(R.id.email_image_3);
    		thirdImage.setImageDrawable(getResources().getDrawable(R.drawable.defaultimage));
    		thirdImage.setContentDescription(getString(R.string.default_image_text));
    		
    		TextView buttonText = (TextView)rootView.findViewById(R.id.email_button);
    		buttonText.setText(getString(R.string.email_button_text));
  		
            return rootView;
        }
        
        
    }
 
}
