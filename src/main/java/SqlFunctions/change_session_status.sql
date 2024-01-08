create or replace function change_session_status(in_id integer, in_status varchar)
returns json
as
$$
begin
	update "session" set status = in_status where id = in_id;
	if found then
		return json_build_object('status', 'success', 'message', 'Updated Session successfullys.', 'data', null);
	else
		return json_build_object('status', 'error', 'message', 'Session not found.', 'data', null);
	end if;
end;
$$
language plpgsql;

--select change_session_status(1234, 'LOGOUT');